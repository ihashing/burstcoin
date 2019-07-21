package brs.http

import brs.Block
import brs.Blockchain
import brs.BlockchainProcessor
import brs.Burst
import brs.peer.Peer
import brs.services.TimeService
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import javax.servlet.http.HttpServletRequest

import brs.http.common.ResultFields.TIME_RESPONSE

internal class GetBlockchainStatus(private val blockchainProcessor: BlockchainProcessor, private val blockchain: Blockchain, private val timeService: TimeService) : APIServlet.JsonRequestHandler(arrayOf(APITag.BLOCKS, APITag.INFO)) {

    internal override fun processRequest(req: HttpServletRequest): JsonElement {
        val response = JsonObject()
        response.addProperty("application", Burst.APPLICATION)
        response.addProperty("version", Burst.VERSION.toString())
        response.addProperty(TIME_RESPONSE, timeService.epochTime)
        val lastBlock = blockchain.lastBlock
        response.addProperty("lastBlock", lastBlock.stringId)
        response.addProperty("cumulativeDifficulty", lastBlock.cumulativeDifficulty.toString())
        response.addProperty("numberOfBlocks", lastBlock.height + 1)
        val lastBlockchainFeeder = blockchainProcessor.lastBlockchainFeeder
        response.addProperty("lastBlockchainFeeder", lastBlockchainFeeder?.announcedAddress)
        response.addProperty("lastBlockchainFeederHeight", blockchainProcessor.lastBlockchainFeederHeight)
        response.addProperty("isScanning", blockchainProcessor.isScanning)
        return response
    }

}
