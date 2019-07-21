package brs

interface Blockchain {

    var lastBlock: Block

    val height: Int

    val transactionCount: Int

    val allTransactions: Collection<Transaction>

    fun getLastBlock(timestamp: Int): Block?

    fun getBlock(blockId: Long): Block?

    fun getBlockAtHeight(height: Int): Block?

    fun hasBlock(blockImplId: Long): Boolean

    fun getBlocks(from: Int, to: Int): Collection<Block>

    fun getBlocks(account: Account, timestamp: Int): Collection<Block>

    fun getBlocks(account: Account, timestamp: Int, from: Int, to: Int): Collection<Block>

    fun getBlockIdsAfter(blockImplId: Long, limit: Int): Collection<Long>

    fun getBlocksAfter(blockId: Long, limit: Int): Collection<Block>

    fun getBlockIdAtHeight(height: Int): Long

    fun getTransaction(transactionId: Long): Transaction?

    fun getTransactionByFullHash(fullHash: String): Transaction?  // TODO add byte[] method

    fun hasTransaction(transactionId: Long): Boolean

    fun hasTransactionByFullHash(fullHash: String): Boolean  // TODO add byte[] method

    fun getTransactions(account: Account, type: Byte, subtype: Byte, blockImplTimestamp: Int, includeIndirectIncoming: Boolean): Collection<Transaction>

    fun getTransactions(account: Account, numberOfConfirmations: Int, type: Byte, subtype: Byte, blockImplTimestamp: Int, from: Int, to: Int, includeIndirectIncoming: Boolean): Collection<Transaction>
}
