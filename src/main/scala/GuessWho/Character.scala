package GuessWho

case class Character(
                      name: String,
                      hasHair: Boolean,
                      hasFacialHair: Boolean,
                      hasGlasses: Boolean,
                      hasHat: Boolean,
                      gender:Gender,
                      eyeColour: EyeColour,
                      hairColour: HairColour
                    )