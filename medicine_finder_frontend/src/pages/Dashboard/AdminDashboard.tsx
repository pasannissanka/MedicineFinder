import React from 'react'
import CardWrapper from '../../components/Card/CardWrapper'
import Navbar from '../../components/Navbar/Navbar'

const AdminDashboard = () => {
  return (
    <div>
        <Navbar />
        <div className='flex justify-center my-2'>
          <div className='w-1/2'>

            <CardWrapper />
          </div>
        </div>
    </div>
  )
}

export default AdminDashboard