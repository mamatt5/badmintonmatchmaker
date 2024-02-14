import React from 'react'
import '../styles/NavigationBar.css'
import { Link } from 'react-router-dom'

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