# 2dTerrainGenerator
A test project I made for generating random 2d terrain for a school project I am working on.


variable description:
LineSize: The width of a single line piece. A higher LineSize creates a smoother surface.
Lacunarity: The factor at which the LineSize reduces for each octave. The higher the Lacunarity, the smaller the LineSize will be at the higher octaves.
Amplitude: The height that the terrain can reach.
Persistance: The factor at which the Amplitude reduces for each octave. The higher the persistance, the less influence the higher Octaves will have on the Amplitude.
Octaves: A line gets generated for each octave using the corresponding LineSizes and Amplitudes based on the Lacunarity and Persistance. The generated lines will then get combined to form one final terrain line. The higher the octave count is, the more detail the terrain will get.
