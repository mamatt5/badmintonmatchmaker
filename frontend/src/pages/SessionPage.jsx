import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import '../styles/SessionPage.css'

// hover on player to show details, bracket, fullname, and beemit
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
        <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className="PlayerCards">
            {removePlayer ? (sortedPlayers.map(player => 

                    <li key={player.id} style={{backgroundColor: 'red'}} onClick={() => removePlayerFromSession(player.id)}>
                        {player.firstName} {player.lastName.charAt(0).toUpperCase()}
                    </li>)) :

            (sortedPlayers.map(player => <li key={player.id}>{player.firstName} {player.lastName.charAt(0).toUpperCase()}</li>))}
                </ul>
        </div>

        <div className='ButtonContainer'>
            {!joinPlayer && <button style={{backgroundColor: 'green'}} 
                onClick={()=>{setUpdated(false);setJoinPlayer(true);setRemovePlayer(false)}}>Join player</button>}

            {!removePlayer && <button style={{backgroundColor: 'red'}} 
                onClick={()=>{setUpdated(false);setJoinPlayer(false);setRemovePlayer(true)}}>Remove player</button>}

            {(!removePlayer && !joinPlayer) && <button style={{backgroundColor: 'grey'}} onClick={()=>navigate('/socialsessions')}>Go back</button>}
        </div>
        
        <div>
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
            
            {removePlayer && <button onClick= {() => {setUpdated(true);updateSession();refreshPage()}} className='UpdateButton'>Update</button>}

            {(joinPlayer || removePlayer) && <button onClick={() => refreshPage()} className='CancelButton'>Back</button>}
        </div>

        {updated && <h3 style={{color: 'green'}}>Player list updated!</h3>}
        </>
    )
}

export default SessionPage
