package movies
import tables._
import tables.Table.get

object Movies extends App {
  val movies =
    new Table("Movies",
      List("title", "director", "year"),
      List(List("The Dark Knight Rises", "Christopher Nolan", 2012),
        List("Le Prestige", "Christopher Nolan", 2006)
      )
    )
  movies.display()

  val actors =
    new Table("Actors",
      List("actor", "title"),
      List(List("Christian Bale", "The Dark Knight Rises"),
        List("Gary Oldman", "The Dark Knight Rises"),
        List("Tom Hardy", "The Dark Knight Rises"),
        List("Hugh Jackman", "Le Prestige"),
        List("Michael Caine", "Le Prestige"),
        List("Christian Bale", "Le Prestige")
      )
    )
  actors.display()

  val test1 = movies.project(List("director", "year"))
  test1.display
  val test2 = movies.product(actors)
  test2.display()
  // Pour la version 2
  // for (code <- List ("tt0154506", "tt0209144", "tt0278504", "tt0372784", "tt0411302", "tt0468569", "tt0482571",
  //     "tt0816692", "tt1345836", "tt1375666", "tt5013056", "tt6663582"))
  //    insert(code)

  // In which movies did Christian Bale and Michael Caine play together?
  val actors1 = actors.alias(Map("actor"->"actor1", "title"->"title1"))
  actors1.display
  val q1 = actors.select(get("actor", actors.attributes, _) == "Christian Bale")
  q1.display
  val q2 = actors1.select(get("actor1", actors1.attributes, _) == "Michael Caine")
  q2.display
  val q3 = q1.join(q2, "title", "title1", _ == _)
  q3.display
  val q = q3.project(List("title"))
  q.display
}