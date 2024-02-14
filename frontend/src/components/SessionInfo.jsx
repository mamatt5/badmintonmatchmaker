import React from 'react'
import '../styles/SessionInfo.css'
import { useState } from 'react'
import { Link } from 'react-router-dom'

const SessionInfo = ({ session }) => {
    const { id, date, place, players } = session
    const [showPlayers, setShowPlayers] = useState(false)

  return (
    <>
    <div className="SessionCards">
      <Link to={`/socialsessions/${id}`}><p>{date} | {place.placeName}</p></Link>
      <button onClick={()=>setShowPlayers(!showPlayers)}>Show players</button>
          {showPlayers && <ul style={{listStyle: 'none'}}>
            {players.map(player =>
              <li key ={player.id}> {player.firstName} </li>)}
          </ul>}
    </div>
    </>
  )
}

export default SessionInfo