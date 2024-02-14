import axios from 'axios'
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const ManagePlaces = () => {
  const navigate = useNavigate()
  const [places, setPlaces] = useState([])
  const [placeName, setPlace] = useState([])

  useEffect(() => {loadPlaces()}, [])

  const loadPlaces = () => {
    axios.get('http://localhost:8088/badminton/places')
    .then(response => setPlaces(response.data))
  }

  const addPlace = () => {
    const place = { placeName }
    axios.post('http://localhost:8088/badminton/places', place)
    .then(response => loadPlaces())
  }

  const removePlace  = (placeid) => {
    axios.delete(`http://localhost:8088/badminton/places/${placeid}`)
    .then(response => loadPlaces())
  }

  return (
    <>
      <h2>Registered badminton courts</h2>
      <ul style={{listStyle: 'none'}}>
        {places.map(place =>
          <li key={place.id}>
            {place.placeName}
            <button onClick={() => removePlace(place.id)}
            style={{
              marginLeft: '10px',
              color: 'red',
              padding: '5px',
              backgroundColor: 'transparent'}}>
              x</button>
          </li>)}
      </ul>

      <div className='ButtonContainer'>
        <input value={placeName} onChange={(e) => setPlace(e.target.value)}/>
        <button style={{backgroundColor: 'green'}} onClick={() => addPlace()}>Add place</button>
        <button style={{backgroundColor: 'gray'}} onClick={() => navigate('/admin/dashboard')}>Go back</button>
      </div>

    </>
  
  )
}

export default ManagePlaces