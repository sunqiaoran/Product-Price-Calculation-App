簡単！手軽！社内営業用の精算アプリ 

アプリの作成背景：B2B営業として、多くの顧客と多種多様な製品を扱う中で、迅速に見積書を提供することが重要です。私の所属する会社では、事務処理用の社内システムで見積書を作成できますが、ログインや情報入力の時間を含めると、約20分ほどかかります。そこで、社内の営業向けにシンプルな精算ソフトを設計し、展示会のように人の流れ
が多く、商談時間が限られている場面でも、素早く見積もりを作成できるようにしたいと考えています。 

実装したい機能は？ MVCモデルに基づき、ユーザーは商品をカートに追加・削除し、精算を行うことができます。 また、ユーザーのパスワード変更や精算履歴の閲覧などの基本機能も含まれており、データベースにはすべてのパスワード変更および精
算の履歴が保存されます。 

機能要件 
1.ログイン機能 
・ログインに失敗した場合はエラーメッセージを表示する。 
・ハッシュ化で、入力されたパスワードは別の値に変換して、DBに格納される。 
2.商品選択機能 
・商品ID/商品名/価格は一覧表示画面に表示される。 
・製品は右クリックから選択できるようにする。 
3.カート内一覧機能 
・商品選択画面とカート内一覧画面はナビゲーションで相互に画面遷移します。 
・間違えて追加してしまった商品はカートから削除することができます。 
・精算ボタンをクリックすると、精算完了画面に遷移して、商品一覧と合計金額が表
示される。 
・精算を完了するとカートの中は空になります。 
4.パスワード変更機能 
・パスワード変更の確認のための再入力が必要とする。 
5．精算履歴機能 
・日付の新しい順に精算履歴が表示される。 
6. ログアウト機能 
・ログイン中のはログアウトできるようにする。 

使用技術 
フロントエンド：HTML, CSS, JSP 
バックエンド：Java 11, Servlet 
データベース：MySQL8.0.34 
サーバー：Tomcat 9 
