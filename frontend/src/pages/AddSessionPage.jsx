import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

// This renders the add session page. It allows the user to create a new session based on the places registered
// on the database through form submission. Note that the numberCourts attribute is unused in this initial version,
// however it is important to store this information for record keeping. This helps organizers to plan ahead how
// many courts are to be reserved to accommodate a certain number of players depending on the event (regular social
// session or tournament).


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

            {/* Lets the user choose only from available places stored in the database. The logic behind
            the ternary expression for value was to fix a bug where when the page was initially rendered
            and the user did not change the selection from the drop-down list, the form crashed. It also
            crashed when "Select place" was chosen again since it will not have a "placeName" property 
            that is available from the database.*/}

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