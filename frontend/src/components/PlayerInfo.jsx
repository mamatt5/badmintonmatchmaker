import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/PlayerInfo.css';

// This component holds the player information retrieved from the database. Note that a player cannot be deleted
// if it is joined either in a game or in a session due to the database design. Also note that beemIt is an unused
// attribute in this initial version, however it is important to hold this information as this is the primary way
// for organizers to contact players regarding payments. It will be utilized in the future development of this
// application. This component also allows the user to edit the player information which takes the user to the
// edit player page.

const PlayerInfo = ({ player, loadPlayers }) => {

    const [errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate();
    const { id, firstName, lastName, bracket, beemIt} = player
  
    useEffect(() => {
      if (errorMessage) {
          const timer = setTimeout(() => {setErrorMessage("");}, 2000);
          return () => clearTimeout(timer)
      }
  }, [errorMessage])

    const deletePlayer = () => {
      axios.delete(`http://localhost:8088/badminton/players/${id}`)
      .then(response => loadPlayers())
      .catch(error => setErrorMessage("Cannot delete player joined in a game or a session."))
    }

  return (
    <>
      <div className="PlayerInfo">
        <p>{lastName}, {firstName} : level {bracket.category} </p>

        <button className="EditButton" onClick={()=>navigate(`/players/edit/${id}`)}>Edit</button>
        <button className="DeleteButton" onClick={()=>deletePlayer()}>Delete</button>

      </div>
      <p style={{color: 'red'}}>{errorMessage}</p>
    </>

  )
}

export default PlayerInfo