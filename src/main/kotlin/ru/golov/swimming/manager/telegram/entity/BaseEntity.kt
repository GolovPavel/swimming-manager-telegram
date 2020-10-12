package ru.golov.swimming.manager.telegram.entity

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: T? = null;

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id;
    }

    /**
     * Hibernate requires the constant hashcode.
     *
     * @see <a href="https://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/chapters/domain/entity.html">Hibernate documentation</a>
     */
    override fun hashCode() = 25

    override fun toString(): String {
        return "BaseEntity(id=$id)"
    }
}
