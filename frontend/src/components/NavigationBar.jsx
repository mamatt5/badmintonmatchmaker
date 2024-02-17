import React from 'react'
import '../styles/NavigationBar.css'
import { Link } from 'react-router-dom'

// This component renders a navigation bar at the top of the window. It should be present everywhere in the application. It allows
// the user to go back to the dashboard and to also logout.

const NavigationBar = ({ logout }) => {
  return (
    <div>
        <ul className="NavigationBar">
            <li><Link to="/admin/dashboard">Dashboard</Link></li>
            <li><Link onClick={logout}>Logout</Link></li>
        </ul>
    </div>
  )
}

export default NavigationBar