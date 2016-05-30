package com.halfcigarette.dietitian.net;

import com.halfcigarette.dietitian.base.JsonResponse;

public class TransactionAsyncHttpJsonHandler extends TransactionAsyncHttpStringHandler {

    /**
     * 自定义http响应处理器
     *
     * @param mTransactionListener
     */
    public TransactionAsyncHttpJsonHandler(TransactionListener mTransactionListener) {
        super(mTransactionListener);
    }

    @Override
    void sendResponse(String responseString) {
        JsonResponse response = JsonResponse.getResponse(responseString);
        if (response.getCode() == ResponseCode.SUCCESS) {
            super.sendResponse(response.getData());
        } else {
            mTransactionListener.onFailure(response.getCode());
        }
    }
}
