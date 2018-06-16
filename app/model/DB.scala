package model
import sorm._

/**
 * Created by rohanp on 2/19/18.
 */
object DB extends Instance( entities = Seq(Entity[Person]()), url = "jdbc:h2:mem:test" )
