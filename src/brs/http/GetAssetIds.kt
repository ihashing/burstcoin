package brs.http

import brs.Asset
import brs.assetexchange.AssetExchange
import brs.util.Convert
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import javax.servlet.http.HttpServletRequest

import brs.http.common.Parameters.FIRST_INDEX_PARAMETER
import brs.http.common.Parameters.LAST_INDEX_PARAMETER
import brs.http.common.ResultFields.ASSET_IDS_RESPONSE

internal class GetAssetIds(private val assetExchange: AssetExchange) : APIServlet.JsonRequestHandler(arrayOf(APITag.AE), FIRST_INDEX_PARAMETER, LAST_INDEX_PARAMETER) {

    internal override fun processRequest(req: HttpServletRequest): JsonElement {

        val firstIndex = ParameterParser.getFirstIndex(req)
        val lastIndex = ParameterParser.getLastIndex(req)

        val assetIds = JsonArray()
        for (asset in assetExchange.getAllAssets(firstIndex, lastIndex)) {
            assetIds.add(Convert.toUnsignedLong(asset.id))
        }
        val response = JsonObject()
        response.add(ASSET_IDS_RESPONSE, assetIds)
        return response
    }

}
