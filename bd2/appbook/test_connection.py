import pymongo
from pymongo import MongoClient
from dotenv import load_dotenv
import os
load_dotenv()

# cluster_url = "mongodb+srv://USER:PASSWORD@cluster0.server.mongodb.net/test"
cluster_url = os.getenv("CLUSTER_URL")


def search():
    cluster = MongoClient(cluster_url)
    db = cluster["db_books"]
    col = db["books"]
    res = col.find({"title": "iPhone in Action"})
    for r in res:
        print(r)


def create():
    cluster = MongoClient(cluster_url)
    db = cluster["db_books"]
    col = db["books"]
    col.insert_one({"title": "Python xyz", "author": "xyz", "categories": ["Programming", "Computer"]})


def delete():
    cluster = MongoClient(cluster_url)
    db = cluster["db_books"]
    col = db["books"]
    col.delete_one({"title": "Python xyz"})


def update():
    cluster = MongoClient(cluster_url)
    db = cluster["db_books"]
    col = db["books"]
    col.find_one_and_update(
        {"title": "Python xyz"},
        {
            "$set": {"categories": ['Web']}
        },
        upsert=False
    )


if __name__ == "__main__":
    print("Oie")
    search()
