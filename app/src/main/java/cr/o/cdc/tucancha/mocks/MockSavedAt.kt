package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.db.model.SavedAt

object MockSavedAt {

    fun getSavedAt(savedAt: Long) = SavedAt(savedAt)
}