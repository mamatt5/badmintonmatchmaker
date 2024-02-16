import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const AddSessionPage = () => {
    const [date, setDate] = useState("")
    const [place, setPlace] = useState("")
    const [numberCourts, setNumberCourts] = useState("")

    const navigate = useNavigate()
    const [places, setPlaces] = useState([])

    useEffect(() => {loadPlaces()}, [])

    const loadPlaces = () => {
        axios.get('http://localhost:8088/badminton/places')
        .then(response => setPlaces(response.data))
    }

    const createSession = (event) => {
        event.preventDefault();
        const session = { date, place, numberCourts }

        axios.post('http://localhost:8088/badminton/sessions', session)
        .then(response => navigate("/socialsessions"))
    }

  return (
    <>
    <h1>Create new session</h1>
    <form onSubmit={createSession} className="AddPlayerForm">
        <div>
            <label>Date: </label>
            <input required type='date' value={date} onChange={(e) => setDate(e.target.value)} />
        </div>
        
        <div>
            <label>Place: </label>
            <select required value={place ? place.placeName : ""} onChange={(e) =>
                setPlace(places.find(place => place.placeName === e.target.value))}>
                        <option disabled value="">Select place</option>
                    {places.map(place => (
                        <option key={place.id} value={place.placeName}>{place.placeName}</option>
                    ))}
            </select>
        </div>

        <div>
            <label>Number of courts: </label>
            <input type='number' min='1' value={numberCourts} onChange={(e) => setNumberCourts(e.target.value)} />
        </div>
        
        <div>
            <button type='submit' style={{backgroundColor: 'green'}}>Submit</button>
            <button style={{backgroundColor: 'gray'}} onClick={() => navigate('/socialsessions')}>Cancel</button>
        </div>
    </form>
    </>
  )
}

export default AddSessionPage