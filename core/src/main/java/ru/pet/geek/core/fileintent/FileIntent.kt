package ru.pet.geek.core.fileintent

interface FileIntent {

    val actions: Set<FileAction>
    val fromFolder: String
    val toFolder: String?
    val newName: String?
    val newExtension: String?

    class Builder {
        private var action: MutableSet<FileAction> = mutableSetOf()
        private var fromFolder: String = ""
        private var toFolder: String? = null
        private var newName: String? = null
        private var newExtension: String? = null

        fun addAction(action: FileAction) = apply { this.action.add(action) }
        fun setFromFolder(folder: String) = apply { this.fromFolder = folder }
        fun setToFolder(path: String?) = apply { this.toFolder = path }
        fun setNewName(name: String?) = apply { this.newName = name }
        fun setNewExtension(ext: String?) = apply { this.newExtension = ext }

        fun build(): FileIntent {
            return FileIntentDefaultImpl(
                actions = action.toSet(),
                fromFolder = fromFolder,
                toFolder = toFolder,
                newName = newName,
                newExtension = newExtension,
            )
        }
    }
}

private class FileIntentDefaultImpl(
    override val actions: Set<FileAction>,
    override val fromFolder: String,
    override val toFolder: String?,
    override val newName: String?,
    override val newExtension: String?,
): FileIntent

