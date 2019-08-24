package com.mif50.barberbooking.data.local

import com.mif50.barberbooking.data.remote.model.Barber
import com.mif50.barberbooking.data.remote.model.Salon
import com.mif50.barberbooking.data.remote.model.User

object DataObject {


     const val TIME_SLOT_COUNT: Int = 20
     var currentUser: User? = null
     var currentSalon: Salon? = null
     var currentBarber: Barber? = null
     var indexViewPager = 0 // this index to view pager should be between 0..3
     var city = ""





     fun convertTimeSlotToString(position: Int): String {
          return  when(position) {
               0 -> " 9:00 - 9:30 "
               1 -> " 9:30 - 10:00 "
               2 -> " 10:00 - 10:30 "
               3 -> " 10:30 - 11:00 "
               4 -> " 11:00 - 11:30 "
               5 -> " 11:30 - 12:00 "
               6 -> " 12:00 - 12:30 "
               7 -> " 12:30 - 1:00 "
               8 -> " 1:00 - 1:30 "
               9 -> " 1:30 - 2:00 "
               10 -> " 2:00 - 2:30 "
               11 -> " 2:30 - 3:00 "
               12 -> " 3:00 - 3:30 "
               13 -> " 3:30 - 4:00 "
               14 -> " 4:00 - 4:30 "
               15 -> " 4:30 - 5:00 "
               16 -> " 5:00 - 5:30 "
               17 -> " 5:30 - 6:00 "
               18 -> " 6:00 - 6:30 "
               19 -> " 6:30 - 7:00 "
               else -> "Closed"
          }
     }
}