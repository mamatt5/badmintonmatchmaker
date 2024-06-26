import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import '../styles/SessionGames.css'

// This file renders the list of games registered in a specific social session to a table. Users are allowed to edit or delete
// specific games. Editing the game would re-render the page into an edit game interface for users to edit the players involved,
// the winners, and the losing pair's score. The mechanics of editing the game is similar to how the Social Session entities are
// edited, which is just through button clicks, except for the score.

// Users are also allowed to create either an empty game, or generate games based on the number of players registered into the
// social session and are levelled according to their skill brackets. Note that there is a tolerance of one level during generation
// as most of the time there are uneven number of players with the same skill brackets.

// User input validation is handled in client side, which can be read from different conditions in the code below. Some validations
// include having not more than four players or more than two winners in a game. Updating a game would require the user to click
// the "Save" button to minimize API calls to just one.

const SessionGames = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const [socialSession, setSocialSession] = useState("")
    const [sessionGames, setSessionGames] = useState([])
    const { sessionid } = useParams()

    const [editRow, setEditRow] = useState(-1)
    const [id, setId] = useState("")
    const [players, setPlayers] = useState([])
    const [winners, setWinners] = useState([])
    const [loseScore, setLoseScore] = useState(0)

    const navigate = useNavigate()

    useEffect(() => {
        loadSession();
        loadSessionGames();
    }, [])

    useEffect(() => {
        if (errorMessage) {
            const timer = setTimeout(() => {setErrorMessage("");}, 2000);
            return () => clearTimeout(timer)
        }
    }, [errorMessage])

    const loadSession = () => {
        axios.get(`http://localhost:8088/badminton/sessions/${sessionid}`)
        .then(response => {setSocialSession(response.data)})
    }

    const loadSessionGames = () => {
        axios.get(`http://localhost:8088/badminton/sessions/${sessionid}/games`)
        .then(response => {setSessionGames(response.data)})
    }

    const generateGames = () => {
        axios.post(`http://localhost:8088/badminton/games/generategames/${sessionid}`, sessionid)
        .then(response => loadSessionGames())
    }

    const deleteGame = (gameId) => {
        axios.delete(`http://localhost:8088/badminton/games/${gameId}`)
        .then(response => loadSessionGames())
    }

    // Since games are listed in a table, this variable handles which game the user wants to edit by grabbing
    // the row index of the rendered game information.
    const toggleEditMode = (index) => {
        setEditRow(index === editRow ? -1 : index)
        setId(sessionGames[index].id)
        setPlayers(sessionGames[index].players)
        setWinners(sessionGames[index].winners)
        setLoseScore(sessionGames[index].loseScore)
    }

    const addPlayerToGame = (playerid) => {

        if (players.length < 4) {
            const updatedGamePlayers = [...players, socialSession.players.find(player => player.id === playerid)]
            setPlayers(updatedGamePlayers)

        } else {

            setErrorMessage("Players for game cannot exceed four.")
        }
    }

    const removePlayerFromGame = (playerid) => {
        const updatedGamePlayers = players.filter(player => player.id !== playerid)
        setPlayers(updatedGamePlayers)
    }

    const addGameWinner = (playerid) => {
        const playerToAdd = players.find(player => player.id == playerid)
        let updatedGameWinners = winners

        if (winners.some(winner => winner.id == playerid)) {
            updatedGameWinners = winners.filter(winner => winner.id !== playerid)
        } else {

            if (winners.length < 2) {
                updatedGameWinners = [...winners, playerToAdd]
            } else {

                setErrorMessage("Winners cannot exceed two players.")
            }
            
        }
        setWinners(updatedGameWinners)
    }

    const updateGame = () => {
        if (loseScore > 0 && winners.length < 2) {
            setErrorMessage("Please select two winning players.")
            return
        }

        // User input validation for updating the game before calling the API.
        if ((players.length == 4 || players.length == 0) && (winners.length == 2 || winners.length == 0)) { 

            const game = { players, winners, loseScore, socialSession, id }
            axios.put(`http://localhost:8088/badminton/games`, game)
            .then(response => {setEditRow(-1);loadSessionGames();navigate(`/socialsessions/${sessionid}/games`)})
    
        } else {
            
            setErrorMessage("Please select four players and two winning players.")
        }
    }

    const createGame = () => {
        const game = { socialSession }

        axios.post(`http://localhost:8088/badminton/games`, game)
        .then(response => {loadSessionGames();navigate(`/socialsessions/${sessionid}/games`)})
    }

    return (
        <>
        <div style={{color: 'red'}}>{errorMessage}</div>

            {/* This is the initial state of the game list page. The editRow variable handles the rendering of the page, if the
            user is editing a game or not */}
            {editRow == -1 && 
            <div>
                <h1>Session games</h1>

                <div className='ButtonContainer'>
                    <button style={{backgroundColor: 'green'}} onClick={() => createGame()}>Create game</button>
                    <button style={{backgroundColor: 'green'}} onClick={() => generateGames()}>Generate games</button>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Players</th>
                            <th>Winners</th>
                            <th>Loser score</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            sessionGames.map((game, index) => (
                                <tr key={game.id}>

                                    <td>{game.id}</td>

                                    <td>
                                        {game.players.map(
                                            player => (
                                                <span key={player.id}>{player.firstName} </span>
                                            )
                                        )}
                                    </td>

                                    <td>
                                        {game.winners.map(
                                            player => (
                                                <span key={player.id}>{player.firstName} </span>
                                            )
                                        )}
                                    </td>

                                    <td>{game.loseScore}</td>

                                    <td className='ButtonContainer'>
                                        <button className='EditButton' onClick={() => toggleEditMode(index)}>Edit</button>
                                        <button onClick={() => deleteGame(game.id)} className='DeleteButton'>Delete</button>
                                    </td>

                                </tr>
                            ))
                        }
                    </tbody>
                </table>

                <div className='ButtonContainer'>
                    <button style={{backgroundColor: 'gray'}} onClick={() => navigate(`/socialsessions/${sessionid}`)}>Go back</button>
                </div>

            </div>}

            {/* This renders the edit game component of the page */}
            {editRow != -1 &&
            <div>

                <h2> Editing game {sessionGames[editRow].id}</h2>

                <div>
                    <h3>Players (click to remove)</h3>

                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className='PlayerCards'>
                        {players.map( player =>
                        
                            <li key={player.id} onClick={() => removePlayerFromGame(player.id)}>
                                {player.firstName} {player.lastName.charAt(0).toUpperCase()}
                            </li>
                            )
                        }
                    </ul>
                </div>

                <div>
                    <h3>Winners</h3>

                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className='PlayerCards'>
                        {players.map( player =>

                            // Provides a visual representation of the player cards if the player is a winner or not
                            <li key={player.id}
                                style={{backgroundColor: winners.find(winner => winner.id == player.id) ? 'green' : 'black'}}
                                onClick={() => addGameWinner(player.id)}>

                                {player.firstName} {player.lastName.charAt(0).toUpperCase()}

                            </li>
                            )
                        }
                    </ul>

                </div>

                <div>

                    <h3>Available players in session (click to add)</h3>

                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap'}} className='PlayerCards'>

                        {/* Filters out the social session players if the player is already in the game */}
                        {socialSession.players.slice().sort((a, b) => a.firstName.localeCompare(b.firstName))
                            .filter(player => !players.find(p=>p.id===player.id))
                            .map( player =>

                                <li key={player.id} style={{backgroundColor: 'gray'}} onClick={() => addPlayerToGame(player.id)}>
                                    {player.firstName} {player.lastName.charAt(0).toUpperCase()}
                                </li>
                                )

                        }
                    </ul>

                </div>

                <div style={{alignItems: 'start'}} className='AddPlayerForm'>

                    <label>Loser score: </label>
                    <input required type='number' min='0' value={loseScore} onChange={(e) => setLoseScore(e.target.value)}></input>

                </div>

                <div className='ButtonContainer'>
                    <button style={{backgroundColor: 'green'}} onClick={() => updateGame()}>Save</button>
                    <button style= {{backgroundColor: 'gray'}} onClick={() => setEditRow(-1)}>Back</button>
                </div>
                
            </div>
            }
        </>
    )
}

export default SessionGames
