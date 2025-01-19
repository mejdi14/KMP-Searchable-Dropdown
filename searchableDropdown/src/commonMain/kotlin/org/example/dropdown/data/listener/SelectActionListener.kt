package org.example.dropdown.data.listener


interface SelectActionListener<T>  {
    fun onItemSelectListener(selectedItem: T)
}