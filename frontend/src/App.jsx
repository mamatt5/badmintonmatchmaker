import React from 'react'
import './App.css'
import LoginPage from './pages/LoginPage.jsx'
import AdminDashboard from './pages/AdminDashboard.jsx'
import Players from './pages/Players.jsx'
import SocialSessions from './pages/SocialSessions.jsx'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import NavigationBar from './components/NavigationBar.jsx'
import AddPlayerPage from './pages/AddPlayerPage.jsx'
import { useState } from 'react'
import EditPlayerPage from './pages/EditPlayerPage.jsx'
import SessionPage from './pages/SessionPage.jsx'
import AddSessionPage from './pages/AddSessionPage.jsx'
import SessionGames from './pages/SessionGames.jsx'
import ManagePlaces from './pages/ManagePlaces.jsx'

function App() {
  const [bearer, setBearer] = useState("")

  return (
    <>
    <div>
      <NavigationBar />
    </div>
    
    <div className="container">
    <Routes>
      <Route path="/login" element={<LoginPage bearer={[bearer, setBearer]}/>} />
      <Route path="/admin/dashboard" element={<AdminDashboard />} />

      <Route path="/players" element={<Players />} />
      <Route path="/players/add" element={<AddPlayerPage />} />
      <Route path="/players/edit/:playerid" element={<EditPlayerPage />} />
      
      <Route path="/socialsessions" element={<SocialSessions />} />
      <Route path="/socialsessions/create" element={<AddSessionPage />} />
      <Route path="/socialsessions/:sessionid" element={<SessionPage />} />
      <Route path="/socialsessions/:sessionid/games" element={<SessionGames />} />

      <Route path="/places" element={<ManagePlaces />} />
    </Routes>
    </div>

    </>
  )
}

export default App
