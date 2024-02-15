import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useParams } from 'react-router-dom'
import '../styles/SessionGames.css'

// max players in the game: 4
// max winners in the game: 2
const SessionGames = () => {
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

    const toggleEditMode = (index) => {
        setEditRow(index === editRow ? -1 : index)
        setId(sessionGames[index].id)
        setPlayers(sessionGames[index].players)
        setWinners(sessionGames[index].winners)
        setLoseScore(sessionGames[index].loseScore)
    }

    const addPlayerToGame = (playerid) => {
        const updatedGamePlayers = [...players, socialSession.players.find(player => player.id === playerid)]
        setPlayers(updatedGamePlayers)
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
            updatedGameWinners = [...winners, playerToAdd]
        }

        setWinners(updatedGameWinners)
    }

    const updateGame = () => {
        const game = { players, winners, loseScore, socialSession, id }

        axios.put(`http://localhost:8088/badminton/games`, game)
        .then(response => {loadSessionGames();navigate(`/socialsessions/${sessionid}/games`)})

    }

    const createGame = () => {
        const game = { socialSession }

        axios.post(`http://localhost:8088/badminton/games`, game)
        .then(response => {loadSessionGames();navigate(`/socialsessions/${sessionid}/games`)})
    }

    return (
        <>
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

            {editRow != -1 &&
            <div>
                <h2> Editing game {sessionGames[editRow].id}</h2>
                <div>
                    <h3>Players (click to remove)</h3>
                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className='PlayerCards'>
                        {players.map( player =>
                            <li key={player.id} onClick={() => removePlayerFromGame(player.id)}>
                                {player.firstName}
                            </li>
                            )
                        }
                    </ul>
                </div>

                <div>
                    <h3>Winners</h3>
                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className='PlayerCards'>
                        {players.map( player =>
                            <li key={player.id}
                                style={{backgroundColor: winners.find(winner => winner.id == player.id) ? 'green' : 'black'}}
                                onClick={() => addGameWinner(player.id)}>
                                {player.firstName}
                            </li>
                            )
                        }
                    </ul>
                </div>

                <div>
                    <h3>Available players in session (click to add)</h3>
                    <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap'}} className='PlayerCards'>
                        {socialSession.players
                            .filter(player => !players.find(p=>p.id===player.id))
                            .map( player =>
                            <li key={player.id} style={{backgroundColor: 'gray'}} onClick={() => addPlayerToGame(player.id)}>
                                {player.firstName}
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
                <button style={{backgroundColor: 'green'}} onClick={() => {updateGame();setEditRow(-1)}}>Save</button>
                <button style= {{backgroundColor: 'gray'}} onClick={() => setEditRow(-1)}>Back</button>
                </div>
                
            </div>
            }
        </>
    )
}

export default SessionGames
