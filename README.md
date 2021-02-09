# Association ACP « Art, culture et philosophie du vivre ensemble »
création d'une application pour ACP association pour permettre d'enregistrer leurs membres avec la création de nouvelles événements.
Il sera aussi permis aux membres de réserver leurs participations aux événement de l'association avec une connexion sécurisée et cryptée.

#Création de la bases de données(PostgreSQL):
Association ACP comportant les tables :
membre,evenement,membre_evenement,reservation et rôles.

Import de base Postgre avec pgAdmin

    1. Se connecter au serveur PGSQL avec pgAdmin

    2. Créer une base de données vierge (clic droit, Create, Database) sauf si elle existe déjà.

    3. Faire un clic droit sur la base de données, Restore.

    4. A l’onglet General, dans le champ Filename, charger le fichier :
    
    la base pour l'application  le nom du fichier est "dump_ACP.sql"
      
    Si le fichier n’apparait pas dans la liste, modifier le Format de fichiers recherché.

    5. Cliquer sur Restore pour importer la base. Un message « Successfully completed » indique que l’opération s’est bien déroulée.
