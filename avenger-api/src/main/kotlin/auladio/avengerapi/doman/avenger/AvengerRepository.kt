package auladio.avengerapi.doman.avenger

import antlr.collections.List

interface AvengerRepository {   // Interface de acesso, devolve o objeto
    fun getDetail(id: Long): Avenger // Função GET
    fun getAvengers(): kotlin.collections.List<Avenger>
    fun create(avenger: Avenger): Avenger
    fun delete(id: Long)
    fun update(avenger: Avenger): Avenger
}