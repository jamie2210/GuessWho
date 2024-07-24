package GuessWho

import scala.annotation.tailrec
import scala.io.StdIn.readLine

class Interface {

// Defining questions for the user to choose

// Questions for Hints


  private val _hints:Map[Int, String] = Map(
    1 -> "Remove random character",
    2 -> "Their name contains letter ... ?",
    0 -> "Go back"
  )


  // Questions about Hair
  private val _hairQuestions:Map[Int, String] = Map(
    1 -> "Do they have hair?",
    2 -> "Do they have blonde hair?",
    3 -> "Do they have brunette hair?",
    4 -> "Do they have red hair?",
    0 -> "Go back"
  )

  // Questions about Eyes
  private val _eyeQuestions:Map[Int, String] = Map(
    1 -> "Do they have blue eyes?",
    2 -> "Do they have brown eyes?",
    3 -> "Do they have green eyes?",
    0 -> "Go back"
  )

  // Questions about Gender
  private val _genderQuestions:Map[Int, String] = Map(
    1 -> "Are they male?",
    2 -> "Are they female?",
    0 -> "Go back"
  )

  // Questions about Facial Hair
  private val _facialQuestions:Map[Int, String] = Map(
    1 -> "Do they have facial hair?",
    0 -> "Go back"
  )

  // Questions about Glasses
  private val _glassesQuestions:Map[Int, String] = Map(
    1 -> "Do they have glasses?",
    0 -> "Go back"
  )

  // Questions about Hat
  private val _hatQuestions:Map[Int, String] = Map(
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
  private def get_user_input(message: String): String = {
    print(message)
    val input = readLine()
    input
  }

  def isInteger(input: String): Either[GuessWhoError, Int] = {
    try {
      Right(input.toInt)
    } catch {
      case _: NumberFormatException => Left(GuessWhoError.AttributeNotInteger)
    }
  }
  def isInRange(intInput:Int, minInclusive:Int, maxInclusive:Int): Either[GuessWhoError, Int] = {
    if (intInput < minInclusive || intInput > maxInclusive) Left(GuessWhoError.AttributeOutOfRange)
    else Right(intInput)
  }

  def matchToAttributeChoice(choiceInt:Int):Either[GuessWhoError, AttributeChoice] = {
    choiceInt match {
      case 1 => Right(AttributeChoice.NameChoice)
      case 2 => Right(AttributeChoice.HairChoice)
      case 3 => Right(AttributeChoice.FacialHairChoice)
      case 4 => Right(AttributeChoice.GlassesChoice)
      case 5 => Right(AttributeChoice.HatChoice)
      case 6 => Right(AttributeChoice.GenderChoice)
      case 7 => Right(AttributeChoice.EyesChoice)
      case 8 => Right(AttributeChoice.HintChoice)
      case _ => Left(GuessWhoError.SomethingWentWrongError)
    }
  }

  def validateAttributeChoice(input:String):Either[GuessWhoError, AttributeChoice] = {
    val minX:Int = 1
    val maxX:Int = 8
    for {
      isInt <- isInteger(input)
      inRange <- isInRange(isInt, minX, maxX)
      choice <- matchToAttributeChoice(inRange)
    } yield choice
  }

  def validateQuestionChoice(input:String, minInclusive:Int, maxInclusive:Int):Either[GuessWhoError, Int] = {
    val minX:Int = minInclusive
    val maxX:Int = maxInclusive
    for {
      isInt <- isInteger(input)
      inRange <- isInRange(isInt, minX, maxX)
    } yield inRange
  }


//  Displays list of attribute options the user can enquire about
  def getAttributeChoice:AttributeChoice = {
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
    val validatedInput:Either[GuessWhoError, AttributeChoice] = validateAttributeChoice(userInput)
    val attributeChoice:AttributeChoice = validatedInput match {
      case Left(error) =>
        error.printErrorMessage()
        getAttributeChoice

      case Right(attributeChoice) => attributeChoice
    }
    attributeChoice
  }

//  Method used to get name of chosen character from user
  def get_name_choice(): String = {
    println("Enter 0 to go back or")
    val name = get_user_input("Enter the name of the character you think it is:  ")
    name.strip().toLowerCase
  }

//  Displays the questions for specified attribute
  private def display_questions(questions: Map[Int, String]): Unit = {
    questions.foreach {
      case (key, value) => println(s"$key : $value")}
  }

  //  Gets the question choice and check it can be converted to type Int
  @tailrec
  private def getQuestionChoice(questions: Map[Int, String], minQuestionNum: Int, maxQuestionNum: Int): Int = {
    display_questions(questions)
    val question_choice = get_user_input("Enter the number of the question you'd like to ask: ")
    val validated_choice:Either[GuessWhoError, Int] = validateQuestionChoice(question_choice, minQuestionNum, maxQuestionNum)
    validated_choice match {
      case Left(error) =>
        error.printErrorMessage()
        getQuestionChoice(questions, minQuestionNum, maxQuestionNum)
      case Right(validNumber) => validNumber
    }
  }

  def getQuestionFromAttribute(attributeChoice: AttributeChoice):Question = {

    val question:Question = attributeChoice match {

      case AttributeChoice.HairChoice =>
        val inputChoiceNumber = getQuestionChoice(_hairQuestions, 0, 4)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.hasHairQuestion
          case 2 => Question.BlondeHairQuestion
          case 3 => Question.BrunetteHairQuestion
          case 4 => Question.RedHairQuestion
        }

      case AttributeChoice.FacialHairChoice =>
        val inputChoiceNumber = getQuestionChoice(_facialQuestions, 0, 1)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.hasFacialHairQuestion
        }
      case AttributeChoice.GlassesChoice =>
        val inputChoiceNumber = getQuestionChoice(_glassesQuestions, 0, 1)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.hasGlassesQuestion
        }
      case AttributeChoice.HatChoice =>
        val inputChoiceNumber = getQuestionChoice(_hatQuestions, 0, 1)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.hasHatQuestion
        }
      case AttributeChoice.GenderChoice =>

        val inputChoiceNumber = getQuestionChoice(_genderQuestions, 0, 2)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.MaleGenderQuestion
          case 2 => Question.FemaleGenderQuestion
        }
      case AttributeChoice.EyesChoice =>
        val inputChoiceNumber = getQuestionChoice(_eyeQuestions, 0, 3)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.BlueEyesQuestion
          case 2 => Question.BrownEyesQuestion
          case 3 => Question.GreenEyesQuestion
        }
      case AttributeChoice.HintChoice =>
        val inputChoiceNumber = getQuestionChoice(_hints, 0, 2)
        inputChoiceNumber match {
          case 0 => Question.GoBackOption
          case 1 => Question.RemoveCharacterHint
          case 2 => Question.LetterFromNameHint
        }
    }
    question
  }
}
