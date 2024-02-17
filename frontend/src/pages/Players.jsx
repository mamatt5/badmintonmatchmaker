import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import PlayerInfo from '../components/PlayerInfo'
import '../styles/Players.css'

// This file renders the player list of the database. It lists all player information registered in the database. Note that
// player is different from users. This file renders each information using the PlayerInfo component. It also allows users
// to add players into the database.

const Players = () => {
    const [playerList, setPlayerList] = useState([])

    const navigate = useNavigate();

    useEffect(() => {loadPlayers()}, [])

    const loadPlayers = () => {
        axios.get('http://localhost:8088/badminton/players')
        .then(response => {setPlayerList(response.data)})
    }

    const sortedList = playerList.slice().sort((a, b) => a.lastName.localeCompare(b.lastName))

  return (
    <>
    <div className='PlayersContainer'>
      <button className="AddPlayerButton" onClick={() => navigate('/players/add')}>Add Player</button>
    </div>

    <div>
        <h1>Players</h1>
        <ul style={{listStyle: 'none'}} className='PlayersList'>
            {sortedList.map(player =>
                <li key = {player.id}><PlayerInfo player={player} loadPlayers={loadPlayers}/></li>)}
        </ul>
    </div>
    </>
    
  )
}

export default Players