#!/usr/bin/env python3

import argparse
import json
from datetime import datetime, timedelta
from pathlib import Path
from PIL import Image
import random
import requests
from uuid import uuid4

from datos_aleatorios import generate_data

base_url = 'https://dog.ceo/api'


def get_breed() -> str:
    url = f'{base_url}/breeds/list/all'
    response = requests.get(url)

    response.raise_for_status()
    response = response.json()

    breed = random.choice(list(response['message'].keys()))

    return breed


def printable_date(date: datetime) -> str:
    return date.strftime('%d/%m/%Y')


def get_dob() -> datetime:
    now = datetime.now()
    oldest = now.replace(year=now.year - 14)

    return datetime.fromtimestamp(random.randrange(
        int(oldest.timestamp()), int(now.timestamp())))


def get_sexo() -> str:
    if bool(random.getrandbits(1)):
        return 'macho'
    return 'hembra'


def get_allergies() -> list:
    allergies = [
        'polen',
        'esporas',
        'cereales',
    ]
    out = set()

    for _ in range(random.randrange(len(allergies))):
        out.add(random.choice(allergies))

    return list(out)


def get_vaccines(dob: datetime) -> dict:
    vaccines = {}
    now = datetime.now()

    sentinel = dob + timedelta(weeks=6)
    if sentinel > now:
        return vaccines
    vaccines['primovacunacion'] = printable_date(sentinel)

    sentinel = sentinel + timedelta(weeks=2)
    if sentinel > now:
        return vaccines
    vaccines['polivalente'] = printable_date(sentinel)

    sentinel = sentinel + timedelta(weeks=4)
    if sentinel > now:
        return vaccines
    vaccines['polivalente+'] = printable_date(sentinel)

    sentinel = sentinel + timedelta(weeks=4)
    if sentinel > now:
        return vaccines
    vaccines['rabia'] = printable_date(sentinel)

    return vaccines


def get_hygiene() -> dict:
    now = datetime.now()

    return {
        'ultimo_bano': printable_date(now - timedelta(weeks=1)),
        'ultimo_cepillado': printable_date(now - timedelta(weeks=1)),
        'ultimo_desparasitado': {
            'interno': printable_date(now - timedelta(weeks=2)),
            'externo': printable_date(now - timedelta(weeks=2)),
        }
    }


def get_mascota() -> dict:
    colors = ['negro', 'blanco', 'marron', 'rubio', ]
    blood_type = ["DEA11", "DEA12", "DEA3",
                  "DEA4", "DEA5", "DEA6", "DEA7", "DEA8", ]
    names = ['Firulais', 'Sansa', 'Hank', 'Sasha',
             'Falucho', 'Coco', 'Tango', 'Toby', 'Pluto', ]
    dob = get_dob()

    return {
        'raza': get_breed(),
        'UUID': str(uuid4()),
        'fecha_nacimiento': printable_date(dob),
        'sexo': get_sexo(),
        'peso': random.randrange(35),
        'color': random.choice(colors),
        'grupo_sanguineo': random.choice(blood_type),
        'nombre': random.choice(names),
        'alergias': get_allergies(),
        'vacunas': get_vaccines(dob),
        'enfermedades': [],
        'higiene': get_hygiene(),
        'productos_no_recomendables': [],
    }


def append_mascota(mascotas_file: Path) -> dict:
    mascotas = []

    if mascotas_file.is_file():
        with mascotas_file.open('r') as f:
            mascotas = json.load(f)

    mascota = get_mascota()

    mascotas.append(mascota)

    with mascotas_file.open('w') as f:
        json.dump(mascotas, f)

    return mascota


def get_pic(mascota_path: Path, mascota: dict):
    url = f'{base_url}/breed/{mascota["raza"]}/images/random'
    response = requests.get(url)

    response.raise_for_status()

    image = requests.get(response.json()['message'], allow_redirects=True)
    image.raise_for_status()

    profile_jpg = Path(mascota_path, 'profile.jpg')
    profile_png = Path(mascota_path, 'profile.png')

    with profile_jpg.open('wb') as f:
        f.write(image.content)

    im = Image.open(profile_jpg)
    im.save(profile_png)
    im.close()

    profile_jpg.unlink()


def main(dir: str):
    mascota = append_mascota(Path(dir, 'mascotas.json'))

    mascota_path = Path(dir, mascota['UUID'])
    mascota_path.mkdir()

    # Generar archivo temeperaturas
    with Path(mascota_path, 'temperaturas.json').open('w') as f:
        json.dump(generate_data(200, 350, 400), f)

    # Generar archivo pulsaciones
    with Path(mascota_path, 'pulsaciones.json').open('w') as f:
        json.dump(generate_data(200, 60, 180), f)

    get_pic(mascota_path, mascota)


if __name__ == '__main__':
    parser = argparse.ArgumentParser()

    parser.add_argument(
        'dir', help='Directory holding the Balanpy configuration', type=str)

    args = parser.parse_args()

    main(args.dir)
