import React from 'react'
import SessionInfo from '../components/SessionInfo.jsx'
import { useState, useEffect } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const SocialSessions = () => {
    const navigate = useNavigate();
    const [sessionList, setSessionList] = useState([])

    useEffect(() => {loadSessions()}, [])
    const sortedSessions = sessionList.slice().sort((a, b) => a.date.localeCompare(b.date))

    const loadSessions = () => {
        axios.get('http://localhost:8088/badminton/sessions')
        .then(response => {setSessionList(response.data)})
    }

  return (
    <>
      <div>
      <button className="AddPlayerButton" onClick={() => navigate("/socialsessions/create")}>New session</button>
      </div>

      <div>
        <h1>Social sessions</h1>
        <ul style={{listStyle: 'none'}}>
          {sortedSessions.map(session =>
            <li key = {session.id}><SessionInfo session = {session} /></li>)}
        </ul>
      </div>
    </>

  )
}

export default SocialSessions