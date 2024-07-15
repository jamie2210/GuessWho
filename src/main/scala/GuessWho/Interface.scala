package GuessWho

import scala.io.StdIn.readLine

class Interface {

  private var _hints:Map[Int, String] = Map(
    1 -> "remove random character",
    2 -> "their name contains...?"
  )

  private var _hairQuestions:Map[Int, String] = Map(
    1 -> "Do they have hair?",
    2 -> "Do they have blonde hair?",
    3 -> "Do they have brunette hair?",
    4 -> "Do they have red hair?",
    0 -> "Go back"
  )

  private var _eyeQuestions:Map[Int, String] = Map(
    1 -> "Do they have blue eyes?",
    2 -> "Do they have brown eyes?",
    3 -> "Do they have green eyes?",
    0 -> "Go back"
  )

  private var _genderQuestions:Map[Int, String] = Map(
    1 -> "Are the male?",
    2 -> "Are the female?",
    0 -> "Go back"
  )

  private var _facialQuestions:Map[Int, String] = Map(
    1 -> "Do they have facial hair?",
    0 -> "Go back"
  )

  private var _glassesQuestions:Map[Int, String] = Map(
    1 -> "Do they have glasses?",
    0 -> "Go back"
  )

  private var _hatQuestions:Map[Int, String] = Map(
    1 -> "Do they have a hat?",
    0 -> "Go back"
  )


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

  def get_user_input(message: String): String = {
    print(message)
    val input = readLine()
    input
  }

  def validate_attribute_choice(choice: String): Int = {
    try{
      choice.toInt
    } catch{
      case e: NumberFormatException => {
        println("Invalid attribute choice, please enter a number e.g. '3' ")
        get_attribute_choice()}
    }
  }

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
    val validatedInput: Int = validate_attribute_choice(userInput)
    validatedInput
  }

  def get_name_choice(): String = {
    println("Enter 0 to go back or")
    val name = get_user_input("Enter the name of the character you think it is:  ")
    name

  }

  def display_questions(questions: Map[Int, String]): Unit = {
    questions.foreach {
      case (key, value) => println(s"$key : $value")}
  }

  def get_question_choice(questions: Map[Int, String]): Int = {
    display_questions(questions)

    val attribute_choice = get_user_input("Enter the number of the question you'd like to ask: ")
    val validated_choice = validate_attribute_choice(attribute_choice)
    validated_choice
  }

  def check_user_guess(guess:Int):Int = {
    val filterNumber:Int = guess match {
      case 2 => get_question_choice(_hairQuestions) match {
        case 0 => 0
        case 1 => 1
        case 2 => 10
        case 3 => 9
        case 4 => 11
        case _ => -1
      }

      case 3 => get_question_choice(_facialQuestions) match {
        case 0 => 0
        case 1 => 2
        case _ => -1
      }
      case 4 => get_question_choice(_glassesQuestions) match {
        case 0 => 0
        case 1 => 3
        case _ => -1
      }
      case 5 => get_question_choice(_hatQuestions) match {
        case 0 => 0
        case 1 => 4
        case _ => -1
      }
      case 6 => get_question_choice(_genderQuestions) match {
        case 0 => 0
        case 1 => 5
        case 2 => 5
        case _ => -1
      }
      case 7 => get_question_choice(_eyeQuestions) match {
        case 0 => 0
        case 1 => 6
        case 2 => 8
        case 3 => 7
        case _ => -1
      }
      case _ => -1
    }
    filterNumber
  }
}
