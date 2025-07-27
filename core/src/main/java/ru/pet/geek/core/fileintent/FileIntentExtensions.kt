package ru.pet.geek.core.fileintent

fun FileIntent.Builder.downloadToTempFolder(url: String) = this.apply {
    this.addAction(FileAction.DOWNLOAD)
    this.setFromFolder(folder = url)
    this.setToFolder(path = TODO())
}

fun FileIntent.Builder.renameToImageFile(id: Int) = this.apply {
    this.setNewName(name = TODO())
}