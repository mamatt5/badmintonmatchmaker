import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import SessionInfo from '../components/SessionInfo.jsx'
import '../styles/SocialSessions.css'

// This renders the social sessions page where it loads all the social sessions stored in the database. The user should be able
// to filter out social sessions based on a period of date. Each social session is rendered into a SessionInfo component for
// object-oriented design, and are sorted based on date. It also allows the user to view all the registered players in the 
// social session. In future developments, pagination should be implemented as the database would continue to grow once it is
// deployed publicly for actual use. The session entity is also passed on as a prop into the SessionInfo component for data
// handling as it contains more functionality, especially when the component gets rendered again into the SessionPage.

const SocialSessions = () => {
    const navigate = useNavigate();
    const [sessionList, setSessionList] = useState([])
    const [startDate, setStartDate] = useState("")
    const [endDate, setEndDate] = useState("")

    useEffect(() => {loadSessions()}, [])

    const sortedSessions = sessionList
      .filter(session => {

        if (!startDate || !endDate) return true;
        const sessionDate = new Date(session.date)
        return sessionDate >= new Date(startDate) && sessionDate <= new Date(endDate)

      })
      .slice().sort((a, b) => a.date.localeCompare(b.date))

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

      <div>
        <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} style={{fontSize: '15px'}} />
        <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} style={{marginLeft: '10px', fontSize: '15px'}} />
      </div>

        <ul style={{listStyle: 'none'}} className='SessionList'>
          {sortedSessions.map(session =>

            <li key={session.id} className='SessionItem'>
              <SessionInfo session = {session} />
            </li>)}

        </ul>

      </div>
    </>

  )
}

export default SocialSessions