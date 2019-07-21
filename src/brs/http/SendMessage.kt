package brs.http

import brs.Account
import brs.Attachment
import brs.BurstException
import brs.services.ParameterService
import com.google.gson.JsonElement

import javax.servlet.http.HttpServletRequest

import brs.http.common.Parameters.RECIPIENT_PARAMETER

internal class SendMessage(private val parameterService: ParameterService, apiTransactionManager: APITransactionManager) : CreateTransaction(arrayOf(APITag.MESSAGES, APITag.CREATE_TRANSACTION), apiTransactionManager, RECIPIENT_PARAMETER) {

    @Throws(BurstException::class)
    internal override fun processRequest(req: HttpServletRequest): JsonElement {
        val recipient = ParameterParser.getRecipientId(req)
        val account = parameterService.getSenderAccount(req)
        return createTransaction(req, account, recipient, 0, Attachment.ARBITRARY_MESSAGE)
    }

}
