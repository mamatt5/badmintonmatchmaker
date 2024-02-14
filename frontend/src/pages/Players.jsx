import axios from 'axios'
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import PlayerInfo from '../components/PlayerInfo'
import '../styles/Players.css'
import { useNavigate } from 'react-router-dom'

const Players = () => {
    const [playerList, setPlayerList] = useState([])

    const navigate = useNavigate();

    useEffect(() => {loadPlayers()}, [])

    const loadPlayers = () => {
        axios.get('http://localhost:8088/badminton/players')
        .then(response => {setPlayerList(response.data)})
    }

  return (
    <>
    <div>
      <button className="AddPlayerButton" onClick={() => navigate('/players/add')}>Add Player</button>
    </div>

    <div>
        <h1>Players</h1>
        <ul style={{listStyle: 'none'}}>
            {playerList.map(player =>
                <li key = {player.id}><PlayerInfo player={player} loadPlayers={loadPlayers}/></li>)}
        </ul>
    </div>
    </>
    
  )
}

export default Players