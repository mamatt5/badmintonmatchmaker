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
