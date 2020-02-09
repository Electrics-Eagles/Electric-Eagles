# kernel maker V 0.0.1 Beta . Written by Alex Zaslavskis

import requests
import shutil


def download_file(url):
    local_filename = url.split('/')[-1]
    with requests.get(url, stream=True) as r:
        with open(local_filename, 'wb') as f:
            shutil.copyfileobj(r.raw, f)

    return local_filename

download_file('https://github.com/torvalds/linux/archive/v5.5.tar.gz')
