import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

// This renders the manage places page and lists all the available places stored in the database. In essence, the places
// entity can be treated also as an event entity wherein the corresponding name can be considered as the name of an event, 
// ie. tournaments. Each social session entity is given a place attribute for easy filtering. Regular social sessions are 
// supposed to be labelled with where the session was held whilst events such as tournaments can also be used in the same 
// manner. In this page, the user can add a place/event and delete said entities as long as the place/event is not involved
// in any social session due to database design.

const ManagePlaces = () => {
  const [errorMessage, setErrorMessage] = useState("")
  const navigate = useNavigate()
  const [places, setPlaces] = useState([])
  const [placeName, setPlaceName] = useState([])

  useEffect(() => {loadPlaces()}, [])

  useEffect(() => {
    if (errorMessage) {
        const timer = setTimeout(() => {setErrorMessage("");}, 2000);
        return () => clearTimeout(timer)
    }
  }, [errorMessage])

  const loadPlaces = () => {
    axios.get('http://localhost:8088/badminton/places')
    .then(response => setPlaces(response.data))
  }

  const addPlace = () => {

      const trimmedName = placeName.trim()

      if ( trimmedName != '') {
        const place = { placeName: trimmedName }
        axios.post('http://localhost:8088/badminton/places', place)
        .then(response => loadPlaces())

    } else {
      setErrorMessage("Field cannot be empty.")
    }
  }

  const removePlace  = (placeid) => {
    axios.delete(`http://localhost:8088/badminton/places/${placeid}`)
    .then(response => loadPlaces())
    .catch(error => setErrorMessage("Cannot delete place/event with games/players."))
  }

  return (
    <>
    
      <h1>Registered courts/events</h1>
      <ul style={{listStyle: 'none'}}>
        {places.map(place =>
          <li key={place.id} style={{fontSize: '20px'}}>
            {place.placeName} 
            <button onClick={() => removePlace(place.id)} 
              style={{marginLeft: '10px', color: 'red', padding: '5px', backgroundColor: 'transparent', outline: 'none', border: 'none'}}>x</button>
          </li>)}
      </ul>

      <div className='ButtonContainer'>
        <div style={{color: 'red'}}>{errorMessage}</div>
        <input value={placeName} onChange={(e) => setPlaceName(e.target.value)}/>
        <button style={{backgroundColor: 'green'}} onClick={() => {addPlace();setPlaceName("")}}>Add place</button>
        <button style={{backgroundColor: 'gray'}} onClick={() => navigate('/admin/dashboard')}>Go back</button>
      </div>

    </>
  
  )
}

export default ManagePlaces