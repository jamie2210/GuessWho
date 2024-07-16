package GuessWho

import scala.io.StdIn.readLine

class Interface {

// Defining questions for the user to choose

// Questions for Hints
  private var _hints:Map[Int, String] = Map(
    1 -> "Remove random character",
    2 -> "Their name contains letter ... ?",
    0 -> "Go back"
  )

  // Questions about Hair
  private var _hairQuestions:Map[Int, String] = Map(
    1 -> "Do they have hair?",
    2 -> "Do they have blonde hair?",
    3 -> "Do they have brunette hair?",
    4 -> "Do they have red hair?",
    0 -> "Go back"
  )

  // Questions about Eyes
  private var _eyeQuestions:Map[Int, String] = Map(
    1 -> "Do they have blue eyes?",
    2 -> "Do they have brown eyes?",
    3 -> "Do they have green eyes?",
    0 -> "Go back"
  )

  // Questions about Gender
  private var _genderQuestions:Map[Int, String] = Map(
    1 -> "Are they male?",
    2 -> "Are they female?",
    0 -> "Go back"
  )

  // Questions about Facial Hair
  private var _facialQuestions:Map[Int, String] = Map(
    1 -> "Do they have facial hair?",
    0 -> "Go back"
  )

  // Questions about Glasses
  private var _glassesQuestions:Map[Int, String] = Map(
    1 -> "Do they have glasses?",
    0 -> "Go back"
  )

  // Questions about Hat
  private var _hatQuestions:Map[Int, String] = Map(
    1 -> "Do they have a hat?",
    0 -> "Go back"
  )

  // Displays to the user:
  // - The remaining characters
  // - Their attributes
  // - The number of remaining players

  def displayCharacters(characters:Seq[Character]):Unit = {
    characters.foreach { character =>
      println(
        s"""
           |Name: ${character.name}
           |Has Hair: ${character.hasHair}
           |Has Facial Hair: ${character.hasFacialHair}
           |Has Glasses: ${character.hasGlasses}
           |Has Hat: ${character.hasHat}
           |Gender: ${character.gender}
           |Eye Colour: ${character.eyeColour}
           |Hair Colour: ${character.hairColour}
    """.stripMargin
      )
    }
    println(s"Number of remaining characters: ${characters.length}")
  }


//  Method to interact with user through CLI
  def get_user_input(message: String): String = {
    print(message)
    val input = readLine()
    input
  }

//  Ensuring the input is of type integer
  def validate_choice(choice: String): Int = {
    try{
      choice.toInt
    } catch{
      case e: NumberFormatException => {
        println("Invalid attribute choice, please enter a number e.g. '3' ")
        get_attribute_choice()}
    }
  }

//  Displays list of attribute options the user can enquire about
  def get_attribute_choice():Int = {
    println(
      s"""
         |1. Name
         |2. Hair
         |3. Facial Hair
         |4. Glasses
         |5. Hat
         |6. Gender
         |7. Eyes
         |
         |8. Get Hint
    """.stripMargin
    )
    val userInput: String = get_user_input("Enter the number of your choice (e.g. '4' to ask about glasses): ")
    val validatedInput: Int = validate_choice(userInput)
    validatedInput
  }

//  Method used to get name of chosen character from user
  def get_name_choice(): String = {
    println("Enter 0 to go back or")
    val name = get_user_input("Enter the name of the character you think it is:  ")
    name

  }

//  Displays the questions for specified attribute
  def display_questions(questions: Map[Int, String]): Unit = {
    questions.foreach {
      case (key, value) => println(s"$key : $value")}
  }

//  Gets the question choice and check it can be converted to type Int
  def get_question_choice(questions: Map[Int, String]): Int = {
    display_questions(questions)

    val attribute_choice = get_user_input("Enter the number of the question you'd like to ask: ")
    val validated_choice = validate_choice(attribute_choice)
    validated_choice
  }

  //  Gets the question choice and
  // Matches to the case number in filterRemaining to perform desired function
  // - If 0, return 0 to go back to attribute options
  // - If Invalid option, return -1 and pick again
  // - If valid, return corresponding case number to filter the characters accordingly
  def check_user_guess(guess:Int):Int = {
    val filterNumber:Int = guess match {
      //      Hair Questions
      case 2 => get_question_choice(_hairQuestions) match {
        case 0 => 0
        case 1 => 1
        case 2 => 10
        case 3 => 9
        case 4 => 11
        case _ => -1
      }
      //      Facial Hair Questions
      case 3 => get_question_choice(_facialQuestions) match {
        case 0 => 0
        case 1 => 2
        case _ => -1
      }
      //      Glasses Questions
      case 4 => get_question_choice(_glassesQuestions) match {
        case 0 => 0
        case 1 => 3
        case _ => -1
      }
      //      Hat Questions
      case 5 => get_question_choice(_hatQuestions) match {
        case 0 => 0
        case 1 => 4
        case _ => -1
      }
      //      Gender Questions
      case 6 => get_question_choice(_genderQuestions) match {
        case 0 => 0
        case 1 => 5
        case 2 => 5
        case _ => -1
      }
      //      Eye Questions
      case 7 => get_question_choice(_eyeQuestions) match {
        case 0 => 0
        case 1 => 6
        case 2 => 8
        case 3 => 7
        case _ => -1
      }
      //      Hint Questions
      case 8 => get_question_choice(_hints) match {
        case 0 => 0
        case 1 => 81
        case 2 => 82
        case _ => -1
      }
      case _ => -1
    }
    filterNumber
  }
}
