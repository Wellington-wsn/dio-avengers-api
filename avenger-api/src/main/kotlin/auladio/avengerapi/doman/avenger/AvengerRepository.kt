package auladio.avengerapi.doman.avenger


interface AvengerRepository {   // Interface de acesso, devolve o objeto
    fun getDetail(id: Long): Avenger // Função GET
    fun getAvengers(): List<Avenger>
    fun create(avenger: Avenger): Avenger
    fun delete(id: Long)
    fun update(avenger: Avenger): Avenger
}