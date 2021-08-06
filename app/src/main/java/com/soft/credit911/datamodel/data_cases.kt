package com.soft.credit911.datamodel

import java.io.Serializable




class data_cases:Serializable {
     val cases: ArrayList<Cases>?=null

     val message: String? = null

     val status: String? = null

    class Cases:Serializable{
         val matters:  ArrayList<Matters>?=null

         val name: String? = null

         val description: String? = null

         val case_type: String? = null

         val status: String? = null
        val date: String? = null
    }
    class Matters:Serializable{
         val name: String? = null

         val agent_details: Agent_details? = null

    }
    class Agent_details:Serializable{
         val last_name: String? = null

         val id: String? = null

         val avatar: String? = null

         val first_name: String? = null

         val email: String? = null

    }
    
}