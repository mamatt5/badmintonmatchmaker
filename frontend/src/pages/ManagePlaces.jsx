import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

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
    if ( placeName != '') {
      const trimmedName = placeName.trim()
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
    <div style={{color: 'red'}}>{errorMessage}</div>
      <h1>Registered courts/events</h1>
      <ul style={{listStyle: 'none'}}>
        {places.map(place =>
          <li key={place.id} style={{fontSize: '20px'}}>
            {place.placeName} 
            <button onClick={() => removePlace(place.id)} style={{marginLeft: '10px', color: 'red', padding: '5px', backgroundColor: 'transparent'}}> x</button>
          </li>)}
      </ul>

      <div className='ButtonContainer'>
        <input value={placeName} onChange={(e) => setPlaceName(e.target.value)}/>
        <button style={{backgroundColor: 'green'}} onClick={() => {addPlace();setPlaceName("")}}>Add place</button>
        <button style={{backgroundColor: 'gray'}} onClick={() => navigate('/admin/dashboard')}>Go back</button>
      </div>

    </>
  
  )
}

export default ManagePlaces