import React from 'react'
import { Link } from 'react-router-dom'
import '../styles/AdminDashboard.css'

const AdminDashboard = () => {
  return (
    <>
    <div>
      <h1>Hello admin!</h1>
    </div>

    <div>
      <ul style={{listStyle: 'none', display: 'flex'}} className='DashboardElements'>
        <li><Link to="/players">Players</Link></li>
        <li><Link to="/socialsessions">Social sessions</Link></li>
        <li><Link to="/places">Manage courts</Link></li>
      </ul>
    </div>
    </>
  )
}

export default AdminDashboard