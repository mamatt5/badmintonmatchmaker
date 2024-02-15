import React from 'react'
import '../styles/PlayerInfo.css'
import { useNavigate } from 'react-router-dom'
import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';

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