import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import '../styles/SessionPage.css'

// This component is one of the more complicated code in this application as it also handles player entities registered in
// the session. User actions are minimized to merely clicks so it makes the interface very intuitive in adding and removing
// players from the session. The player list is also sorted by first name. The color of the player cards also changes color
// to provide the user a visual cue on whether they are adding or removing players. Note that to save any modifications, the
// user has to click the "Update" button, otherwise any change would get discarded. This reduces API calls to a single
// call and only when the "Update" button was clicked. Also note that a session cannot be deleted if there are any games
// registered in the session.

// Incuded in this page is getting the list of all players registered into the database. This is rendered if the user wants
// to register other players into the session. The list if filtered if the player is already registered into the session.

// Some features of this page include rendering different components whenever "Join player" or "Remove player" button is
// clicked which is why this file contains more lines of code compared to the other components. It is also from this page
// where the user can navigate to the list of games in that specific session.

const SessionPage = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate();
    const {sessionid} = useParams();
    const id = sessionid;
    const [date, setDate] = useState("")
    const [numberCourts, setNumberCourts] = useState("")
    const [place, setPlace] = useState("")
    const [players, setPlayers] = useState([])
    const [playerList, setPlayerList] = useState([])
    const [availablePlayers, setAvailablePlayers] = useState([])
    const [updated, setUpdated] = useState(false)

    const [removePlayer, setRemovePlayer] = useState(false)
    const [joinPlayer, setJoinPlayer] = useState(false)

    useEffect(()=> {loadSession();loadPlayerList()},[])
    const sortedPlayerList = playerList.slice().sort((a, b) => a.firstName.localeCompare(b.firstName))
    const sortedPlayers = players.slice().sort((a, b) => a.firstName.localeCompare(b.firstName))

    useEffect(()=> {setAvailablePlayers(sortedPlayerList.filter(player => !players.find(p=>p.id===player.id) ) )}, [playerList, players])

    useEffect(() => {
        if (errorMessage) {
            const timer = setTimeout(() => {setErrorMessage("");}, 2000);
            return () => clearTimeout(timer)
        }
    }, [errorMessage])

    const loadSession = () => {
        axios.get(`http://localhost:8088/badminton/sessions/${sessionid}`)
        .then(response => {
            const { date, numberCourts, place, players } = response.data;
            setDate(date);
            setNumberCourts(numberCourts)
            setPlace(place);
            setPlayers(players);
        })
    }

    const loadPlayerList = () => {
        axios.get('http://localhost:8088/badminton/players')
        .then(response => setPlayerList(response.data))
    }

    const removePlayerFromSession = (playerId) => {
        const updatedPlayers = players.filter(player => player.id !== playerId);
        setPlayers(updatedPlayers);
    };

    const addPlayerToSession = (playerId) => {
        const updatedPlayers = [...players, playerList.find(player => player.id === playerId)]
        setPlayers(updatedPlayers)

        const updatedAvailablePlayers = availablePlayers.filter(player => player.id !== playerId);
        setAvailablePlayers(updatedAvailablePlayers)
    }

    const updateSession = () => {
        const session = { id, date, numberCourts, place, players }

        axios.put('http://localhost:8088/badminton/sessions', session)
        .then(response => navigate(`/socialsessions/${sessionid}`))
    }

    const deleteSession = () => {
        axios.delete(`http://localhost:8088/badminton/sessions/${id}`)
        .then(response => {navigate('/socialsessions')})
        .catch(error => setErrorMessage("Cannot delete session with games."))
    }

    const refreshPage = () => {
        setJoinPlayer(false);
        setRemovePlayer(false);
        navigate(`/socialsessions/${sessionid}`)
    }

    return (
        <>
        <div style={{color: 'red', marginBottom: '5px'}}>{errorMessage}</div>

        <div>
            <button className='DeleteButton' onClick={() => deleteSession()}>Delete session</button>
            <button style={{backgroundColor: 'blue', marginLeft: '10px'}} onClick={() => navigate(`/socialsessions/${sessionid}/games`)}>
                Show games</button>
        </div>

        { (!joinPlayer && !removePlayer) &&
        <>
            <h2>Date: {date}</h2>
            <h2>Court: {place.placeName}</h2>
        </>}

        {joinPlayer && <h2>Registered players</h2>}
        {removePlayer && <h2>Click the names of the players you want to remove from the session</h2>}
        
        <div>
            {/* Renders the list of players registered into the session */}
            <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className="PlayerCards">

                {/* Appearance changes if "Remove Player" button is clicked and turns the player cards into clickable components */}
                {removePlayer ? (sortedPlayers.map(player => 

                        <li key={player.id} style={{backgroundColor: 'red'}} onClick={() => removePlayerFromSession(player.id)}>
                            {player.firstName} {player.lastName.charAt(0).toUpperCase()}
                        </li>)) :

                (sortedPlayers.map(player => <li key={player.id}>{player.firstName} {player.lastName.charAt(0).toUpperCase()}</li>))}
            </ul>
        </div>

        <div className='ButtonContainer'>

            {/* Renders different actions available for the user */}
            {!joinPlayer && <button style={{backgroundColor: 'green'}} 
                onClick={()=>{setUpdated(false);setJoinPlayer(true);setRemovePlayer(false)}}>Join player</button>}

            {!removePlayer && <button style={{backgroundColor: 'red'}} 
                onClick={()=>{setUpdated(false);setJoinPlayer(false);setRemovePlayer(true)}}>Remove player</button>}

            {(!removePlayer && !joinPlayer) && 
                <button style={{backgroundColor: 'grey'}} onClick={()=>navigate('/socialsessions')}>Go back</button>}
        </div>
        
        <div>

            {/* Renders the list of players registered in the database that is not registered in the session. The player
            cards are clickable so users only have to click the names of the players they want to add. */}
            {joinPlayer &&
                (
                    <>
                    <h3>Available players to add (click to add)</h3>
                    <ul style={{listStyle: 'none', display: 'flex', flexWrap: 'wrap'}} className='PlayerCards'>
                    {
                        availablePlayers.map(player => 

                            <li key={player.id} style={{backgroundColor: 'green'}} onClick={() => addPlayerToSession(player.id)}>
                                {player.firstName} {player.lastName.charAt(0).toUpperCase()}
                            </li>)
                    }
                    </ul>
                    <button onClick= {() => {setUpdated(true);updateSession();refreshPage()}} className='UpdateButton'>Update</button>
                    </>
                )
            }
            
            {removePlayer && 
                <button onClick= {() => {setUpdated(true);updateSession();refreshPage()}} className='UpdateButton'>Update</button>}

            {(joinPlayer || removePlayer) && 
                <button onClick={() => refreshPage()} className='CancelButton'>Back</button>}
                
        </div>

        {updated && <h3 style={{color: 'green'}}>Player list updated!</h3>}
        </>
    )
}

export default SessionPage
