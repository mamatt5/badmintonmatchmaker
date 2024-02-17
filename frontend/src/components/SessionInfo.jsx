import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import '../styles/SessionInfo.css'

// This component renders the social session information into a link which when clicked will take the user to the
// SessionPage. This also allows user to have a quick view of the players registered into the session.

const SessionInfo = ({ session }) => {
    const { id, date, place, players } = session
    const [showPlayers, setShowPlayers] = useState(false)

  return (
    <>

    <div className="SessionInfo">

      <Link to={`/socialsessions/${id}`}><p>{date} | {place.placeName}</p></Link>

      <button onClick={()=>setShowPlayers(!showPlayers)}>Show players</button>

          {showPlayers && 

          <ul style={{listStyle: 'none'}}>
            {players.map(player => <li key ={player.id}> {player.firstName} </li>)}
          </ul>

          }
          
    </div>
    </>
  )
}

export default SessionInfo