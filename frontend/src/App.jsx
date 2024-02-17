import React, { useEffect, useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import './App.css'
import NavigationBar from './components/NavigationBar.jsx'
import AddPlayerPage from './pages/AddPlayerPage.jsx'
import AddSessionPage from './pages/AddSessionPage.jsx'
import AdminDashboard from './pages/AdminDashboard.jsx'
import EditPlayerPage from './pages/EditPlayerPage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import ManagePlaces from './pages/ManagePlaces.jsx'
import Players from './pages/Players.jsx'
import RegisterUser from './pages/RegisterUser.jsx'
import SessionGames from './pages/SessionGames.jsx'
import SessionPage from './pages/SessionPage.jsx'
import SocialSessions from './pages/SocialSessions.jsx'

// This application aims to record badminton matches and also allows users to generate matches based on their skill level.
// Using this application would help in organizing badminton tournaments as it automates levelling of the players and generates
// matches accordingly. It also provides a tangible reference on how good the players are in the sport instead of relying on mere
// word of mouth. This application should be runnable on any browser. All user input validations are done on the front end to 
// concentrate data processing on the client side.This app is also designed with simplicity in mind. Components and pages are
// rendered in the most simplistic way possible using only basic css elements. It was also aimed to be as intuitive as possible,
// minimizing key strokes by utilizing just button clicks to perform various actions.
//
// This application was built on the following infrastructure:
// Frontend: React Vite
// Backend: Java, Spring framework
// Database: MySQL
// Code editors: Eclipse, VS Code
// Other tools: Postman, Git, PNPM
// Operating system: Windows

// This app can only be used if the user is logged in. A bearer token is stored locally once successfully logged in. This bearer token
// would be removed once the user decides to log out.

// In the future:
// -integrate a player entity into a user.
// -assign admin/user role for all users and restrict some functionalities accordingly
// -display onHover additional player details (ie. beemIt, player bracket)
// -pagination
// -integrate into a mobile application
// -implement doubles matching where pairs are matched with other pairs (restriction where two players must always be together in a game)
// -improve the overall appearance of the application by using MaterialUI

function App() {
  const [bearer, setBearer] = useState("")

  const logout = () => {
    setBearer('')
  }

  useEffect(() => {
    const storedBearer = localStorage.getItem("bearer");
    if (storedBearer) {
      setBearer(storedBearer);
    }
  }, []);

  useEffect(() => {localStorage.setItem("bearer", bearer);}, [bearer]);
  
  return (
    <>
    {!!bearer &&
    <>
    <div>
      <NavigationBar logout={logout}/>
    </div>
    </>}
    
    <div className="container">
    <Routes>
      <Route path="/login" element={<LoginPage bearer={[bearer, setBearer]}/>} />
      <Route path="/register" element={<RegisterUser />} />

      {!!bearer ?
      (
        <>
          <Route path="/admin/dashboard" element={<AdminDashboard />}/>

          <Route path="/players" element={<Players />}/>
          <Route path="/players/add" element={<AddPlayerPage />}/>
          <Route path="/players/edit/:playerid" element={<EditPlayerPage />}/>

          <Route path="/socialsessions" element={<SocialSessions />}/>
          <Route path="/socialsessions/create" element={<AddSessionPage />}/>
          <Route path="/socialsessions/:sessionid" element={<SessionPage />}/>
          <Route path="/socialsessions/:sessionid/games" element={<SessionGames />}/>

          <Route path="/places" element={<ManagePlaces />} />
        </>
      ) : (

        <Route path="*" element={<LoginPage bearer={[bearer, setBearer]} />} />
      )
      }


    </Routes>
    </div>

    </>
  )
}

export default App
