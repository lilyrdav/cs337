import hashlib

def hashString(message, algorithm):
    # Instantiate the specified algorithm
    digest = hashlib.new(algorithm)
    # Compute the hash value of message
    digest.update(message.encode('utf-8'))
    # Convert hash value to a hex string and return it
    return digest.hexdigest()

def main():
    message = "It was the best of times, it was the worst of times."
    algorithm = "md5"
    hashValue = hashString(message, algorithm)
    print(hashValue, "   ", message)

    algorithm = "sha256"
    hashValue = hashString(message, algorithm)
    print(hashValue, "   ", message)

if __name__ == '__main__':
    main()
