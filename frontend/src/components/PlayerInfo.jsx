import React from 'react'
import '../styles/PlayerInfo.css'
import { useNavigate } from 'react-router-dom'
import axios from 'axios';

const PlayerInfo = ({ player }) => {
    const navigate = useNavigate();
    const { id, firstName, lastName, bracket, beemIt} = player
  
    const deletePlayer = () => {
      axios.delete(`http://localhost:8088/badminton/players/${id}`)
      .then(response => {navigate('/players');window.location.reload()})
    }

  return (
    <div className="PlayerInfo">
    <p>{firstName} {lastName}, level {bracket.category} </p>
    <button className="EditButton" onClick={()=>navigate(`/players/edit/${id}`)}>Edit</button>
    <button className="DeleteButton" onClick={()=>deletePlayer()}>Delete</button>
    </div>
  )
}

export default PlayerInfo