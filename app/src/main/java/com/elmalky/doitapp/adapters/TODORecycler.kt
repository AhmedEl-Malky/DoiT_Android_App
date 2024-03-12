package com.elmalky.doitapp.adapters

import com.elmalky.doitapp.R
import com.elmalky.doitapp.models.dataBase.entities.TODO

class TODORecycler(val items: List<TODO>, val listener: TODOInteractions) :
    BaseRecyclerAdapter<TODO>(items, listener) {
    override val layoutId = R.layout.item_todo
}

interface TODOInteractions : BaseItemInteractions