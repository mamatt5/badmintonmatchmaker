import React from 'react'
import { Link } from 'react-router-dom'
import '../styles/AdminDashboard.css'

// This file renders the dashboard to where the user is navigated to once successfully logged in.

const AdminDashboard = () => {
  return (
    <>
    <div>
      <h1>Prookies dashboard</h1>
    </div>

    <div>
      <ul style={{listStyle: 'none', display: 'flex'}} className='DashboardElements'>
        <li><Link to="/players">Players</Link></li>
        <li><Link to="/socialsessions">Social sessions</Link></li>
        <li><Link to="/places">Manage courts/events</Link></li>
      </ul>
    </div>
    </>
  )
}

export default AdminDashboard