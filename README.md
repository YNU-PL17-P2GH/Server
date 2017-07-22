# Server
zipファイルで更新します

メモ
想定している使い方はConnectOpe.javaを考えています。
setFlagで"operation"というキーにサーバへの要求を格納する。
オブジェクトをサーバは受信して、"operation"キーのフラグを確認getFlagして
要求を満たす処理を行う。
クライアントへその処理が行われたかどうかを処理特有のキーにaccept,rejectと
フラグに入れて返送する。
これを繰り返す。
期待されてた実装とは違う点があると思うので教えてください。

実際にサーバ側で必要なプログラムは
Server.java
Receiver.java
ServerData.java
SaveData.java
です。

ConnectOpe.java
Connection.java
これらはテストのために使いました。
クライアントはこんなものを想定しています。
