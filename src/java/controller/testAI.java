/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edano
 */
public class testAI {
    public String AIresponse(){
        return """
                 {"Quiz Name":"A numbers game.",
               	  "Quiz Questions":[
                        {"Question": "what is 2+2?",
               		"alternatives":["3","5","4","juckport 1 trillion$"],
               		"correct answer": "1",
               		"difficulty level": "1"
               		},
                               
                        {"Question": "what is 9+10?",
               		"alternatives":["21","19","2","you stupid"],
               		"correct answer": "0",
               		"difficulty level": "1"
               		},
                              
                        {"Question": "Are you dumb?",
               		"alternatives":["no","maybe","why does that matter? bitch","yeag"],
               		"correct answer": "3",
               		"difficulty level": "2"
               		}
               	  ]
                 }
               """;
    }
}
