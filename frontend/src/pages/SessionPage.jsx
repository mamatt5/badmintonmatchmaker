import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios'
import '../styles/SessionPage.css'
import { useNavigate } from 'react-router-dom'

// hover on player to show details, bracket, fullname, and beemit
const SessionPage = () => {
    const navigate = useNavigate();
    const {sessionid} = useParams();
    const id = sessionid;
    const [date, setDate] = useState("")
    const [numberCourts, setNumberCourts] = useState("")
    const [place, setPlace] = useState("")
    const [players, setPlayers] = useState([])
    const [playerList, setPlayerList] = useState([])
    const [availablePlayers, setAvailablePlayers] = useState([])

    const [removePlayer, setRemovePlayer] = useState(false)
    const [joinPlayer, setJoinPlayer] = useState(false)

    useEffect(()=> {loadSession();loadPlayerList()},[])
    useEffect(()=> {setAvailablePlayers(playerList.filter(player => !players.find(p=>p.id===player.id) ) )}, [playerList, players])

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
    }

    const refreshPage = () => {
        setJoinPlayer(false);
        setRemovePlayer(false);
        navigate(`/socialsessions/${sessionid}`)
    }

    return (
        <>
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
        {joinPlayer && <h2>Chosen players</h2>}
        {removePlayer && <h2>Click the names of the players you want to remove from the session</h2>}
        {}
        <div>
        <ul style={{ listStyle: 'none', display: 'flex', flexWrap: 'wrap' }} className="PlayerCards">
            {removePlayer ? (players.map(player => 

                    <li key={player.id} style={{backgroundColor: 'red'}} onClick={() => removePlayerFromSession(player.id)}>
                        {player.firstName}
                    </li>)) :

            (players.map(player => <li key={player.id}>{player.firstName}</li>))}
                </ul>
        </div>

        <div className='ButtonContainer'>
            {!joinPlayer && <button style={{backgroundColor: 'green'}} 
                onClick={()=>{setJoinPlayer(true);setRemovePlayer(false)}}>Join player</button>}

            {!removePlayer && <button style={{backgroundColor: 'red'}} 
                onClick={()=>{setJoinPlayer(false);setRemovePlayer(true)}}>Remove player</button>}

            {(!removePlayer && !joinPlayer) && <button style={{backgroundColor: 'grey'}} onClick={()=>navigate('/socialsessions')}>Go back</button>}
        </div>
        
        <div>
            {joinPlayer &&
                (
                    <>
                    <h3>Available players to add</h3>
                    <ul style={{listStyle: 'none', display: 'flex', flexWrap: 'wrap'}} className='PlayerCards'>
                    {
                        availablePlayers.map(player => 

                            <li key={player.id} style={{backgroundColor: 'green'}} onClick={() => addPlayerToSession(player.id)}>
                                {player.firstName}
                            </li>)
                    }
                    </ul>
                    <button onClick= {() => {updateSession();refreshPage()}} className='UpdateButton'>Update</button>
                    </>
                )
            }
            
            {removePlayer && <button onClick= {() => {updateSession();refreshPage()}} className='UpdateButton'>Update</button>}

            {(joinPlayer || removePlayer) && <button onClick={() => refreshPage()} className='CancelButton'>Cancel</button>}
        </div>
        </>
    )
}

export default SessionPage